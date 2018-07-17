package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().HomePage();
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstName("Serhiy").withSecondName("Tymoshenko")
                 .withAddress("17. Sichovy str.\n116 apt")
                 .withEmail("serdtimosh@gmail.com")
                 .withPhone1("+0472313950").withPhone2("+380945643839")
                 .withGroup("test1"), true);
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