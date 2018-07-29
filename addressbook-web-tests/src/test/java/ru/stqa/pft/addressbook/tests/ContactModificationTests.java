package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      Groups groups = app.db().groups();
      app.goTo().HomePage();
      if (groups.size() == 0) {
         app.goTo().groupPage();
         GroupData newGroup = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
         app.group().create(newGroup);
      } else if (app.db().contacts().size() == 0) {
         app.goTo().HomePage();
         app.contact().create(new ContactData()
                 .withFirstName("Serhiy").withLastName("Tymoshenko")
                 .withAddress("17. Sichovy str.\n116 apt")
                 .withEmail("serdtimosh@gmail.com").withEmail2("serdtimosh+1@gmail.com").withEmail3("serdtimosh+2@gmail.com")
                 .withHomePhone("+0472313950").withMobilePhone("+380945643839").withWorkPhone("+380975643850")
                 .inGroup(groups.iterator().next())
                 .withPhoto(new File("src/test/resources/duck.png")), true);
      }
   }

   @Test
   public void testContactModification() {
      Contacts before = app.db().contacts();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstName("John")
              .withLastName("Doe")
              .withAddress("17. Sichovy str.\r\n218 apt")
              .withEmail("waskos@gmail.com").withEmail2("waskos+1@gmail.com").withEmail3("waskos+2@gmail.com")
              .withHomePhone("+0472313945").withMobilePhone("+380945643856").withWorkPhone("+380955653950")
              .inGroup(null)
              .withPhoto(new File("src/test/resources/duck.png"));
      app.contact().modify(contact);
      Contacts after = app.db().contacts();

      assertThat(after.size(), equalTo(before.size()));
      assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
   }

}
