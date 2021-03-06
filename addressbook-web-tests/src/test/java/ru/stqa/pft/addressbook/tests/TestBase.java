package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {


   protected static final ApplicationManager app
           = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
   Logger logger = LoggerFactory.getLogger(TestBase.class);
   Random rand = new Random();
   int n = rand.nextInt(50) + 1;

   @BeforeSuite
   public void setUp() throws Exception {
      app.init();
   }

   @AfterSuite
   public void tearDown() {
      app.stop();
   }

   @BeforeMethod(alwaysRun = true)
   public void logTestStart(Method m, Object[] p) {
      logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

   }

   @AfterMethod(alwaysRun = true)
   public void logTestStop(Method m) {
      logger.info("Stop test " + m.getName());
   }

   public void verifyGroupListInUi() {
      if (Boolean.getBoolean("verifyUI")) {
         Groups dbGroups = app.db().groups();
         Groups uiGroups = app.group().all();
         assertThat(uiGroups, equalTo(dbGroups.stream()
                 .map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
      }

   }

   public void ensureContactPresent() {
      if (app.db().contacts().size() == 0) {
         app.goTo().HomePage();
         app.contact().create(new ContactData()
                 .withFirstName("Serhiy").withLastName("Tymoshenko")
                 .withAddress("17. Sichovy str.\r\n116 apt")
                 .withEmail("serdtimosh@gmail.com").withEmail2("serdtimosh+1@gmail.com").withEmail3("serdtimosh+2@gmail.com")
                 .withHomePhone("+0472313950").withMobilePhone("+380945643839").withWorkPhone("+380975643850")
                 .inGroup(app.db().groups().iterator().next())
                 .withPhoto(new File("src/test/resources/duck.png")), true);
      }
   }

   public void ensureGroupPresent() {
      if (app.db().groups().size() == 0) {
         app.goTo().groupPage();
         GroupData newGroup = new GroupData().withName("test 1");
         app.group().create(newGroup);
      }
   }

   /*Ensure, that there is some contact not included in all groups
    * and if there is no - create new group*/
   public void ensureContactWithNotAllGroups() {
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();
      boolean freeGroupFound = false;
      for (ContactData contact : contacts) {
         if (contact.getGroups().size() != groups.size()) {
            freeGroupFound = true;
            break;
         }
      }
      if (!freeGroupFound) {
         app.goTo().groupPage();
         app.group().create(new GroupData().withName("test " + n));
      }
   }

   /*Ensure, that there is some Contact added to some group*/
   public void ensureContactInGroup() {
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();
      boolean occupiedGroupFound = false;
      for (ContactData contact : contacts) {
         if (contact.getGroups().size() > 0) {
            occupiedGroupFound = true;
            break;
         }
      }
      if (!occupiedGroupFound) {
         app.goTo().HomePage();
         app.contact().addToGroup(contacts.iterator().next(), groups.iterator().next());
      }
   }


}
