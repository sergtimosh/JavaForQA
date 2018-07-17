package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      ContactData contact = new ContactData()
              .withFirstName("Arsen")
              .withSecondName("Tymoshenko")
              .withAddress("17. Sichovy str.\n116 apt")
              .withEmail("serdtimosh@gmail.com")
              .withPhone1("+0472313950")
              .withPhone2("+380945643839")
              .withGroup("test1");
      app.contact().create(contact, true);
      Contacts after = app.contact().all();

      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }

}
