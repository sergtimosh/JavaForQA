package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      Groups groups = app.db().groups();
      app.goTo().HomePage();
      if (groups.size() == 0) {
         app.goTo().groupPage();
         GroupData newGroup = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
         app.group().create(newGroup);
      } else if (app.db().contacts().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstName("Serhiy").withLastName("Tymoshenko")
                 .withAddress("17. Sichovy str.\r\n116 apt")
                 .withAllEmails("serdtimosh@gmail.com")
                 .withHomePhone("+0472313950").withMobilePhone("+380945643839").withWorkPhone("+380975643850")
                 .inGroup(groups.iterator().next()), true);
      }
   }

   @Test
   public void testContactDeletion() {
      Contacts before = app.contact().all();
      ContactData deletedContact = before.iterator().next();
      app.contact().delete(deletedContact);
      app.goTo().HomePage();
      Contacts after = app.contact().all();

      assertThat(after.size(), equalTo(before.size() - 1));
      assertThat(after, equalTo(before.without(deletedContact)));
   }

}