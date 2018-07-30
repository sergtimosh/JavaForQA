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
      ensureGroupPresent();
      ensureContactPresent();
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