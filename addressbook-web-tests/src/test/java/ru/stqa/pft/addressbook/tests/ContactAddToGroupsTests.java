package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupsTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      ensureGroupPresent();
      ensureContactPresent();
      ensureContactWithNotAllGroups();
      app.goTo().HomePage();
   }

   @Test
   public void testContactAddToGroups() {
      Contacts before = app.db().contacts();
      ContactData contactFound = findContactNotInAllGroups();
      GroupData groupFound = findGroupWithout(contactFound);
      app.contact().addToGroup(contactFound, groupFound);
      ContactData modifiedContact = contactFound.inGroup(groupFound);
      app.goTo().HomePage();
      Contacts after = app.db().contacts();

      assertThat(after.size(), equalTo(before.size()));
      assertThat(after, equalTo(before.without(contactFound).with(modifiedContact)));

   }


   private ContactData findContactNotInAllGroups() {
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();
      ContactData contactFound = null;
      for (ContactData contact : contacts) {
         if (contact.getGroups().size() != groups.size()) {
            contactFound = contact;
            break;
         }
      }
      return contactFound;
   }

   private GroupData findGroupWithout(ContactData contact) {
      Groups groups = app.db().groups();
      GroupData selectedGroup = null;
      for (GroupData group : groups) {
         if (!group.getContacts().contains(contact)) {
            selectedGroup = group;
            break;
         }
      }
      return selectedGroup;
   }


}