package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      ensureGroupPresent();
      ensureContactPresent();
      ensureContactInGroup();
      app.goTo().HomePage();
   }

   @Test
   public void testContactRemoveFromGroup() {
      Contacts before = app.db().contacts();
      ContactData contactFound = findContactWithGroup();
      Contacts beforeWithoutFound = before.without(contactFound);
      GroupData groupToRemoveFrom = contactFound.getGroups().iterator().next();
      app.contact().removeFromGroup(contactFound, groupToRemoveFrom);
      ContactData modifiedContact = contactFound.fromGroup(groupToRemoveFrom);
      Contacts after = app.db().contacts();

      assertThat(after.size(), equalTo(before.size()));
      assertThat(after, equalTo(beforeWithoutFound.with(modifiedContact)));
   }

   public ContactData findContactWithGroup() {
      Contacts contacts = app.db().contacts();
      ContactData contactFound = null;
      for (ContactData contact : contacts) {
         if (contact.getGroups().size() > 0) {
            contactFound = contact;
            break;
         }
      }
      return contactFound;
   }

}
