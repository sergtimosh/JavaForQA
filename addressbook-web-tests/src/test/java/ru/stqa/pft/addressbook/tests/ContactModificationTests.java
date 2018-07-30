package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().HomePage();
      ensureGroupPresent();
      ensureContactPresent();
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
