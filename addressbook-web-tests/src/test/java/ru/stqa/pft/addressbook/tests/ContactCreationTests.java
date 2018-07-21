package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

   @Test
   public void testContactCreation() {
      app.goTo().HomePage();
      Contacts before = app.contact().all();
      File photo = new File("src/test/resources/duck.png");
      ContactData contact = new ContactData()
              .withFirstName("Arsen").withLastName("Tymoshenko")
              .withAddress("17. Sichovy str.\n116 apt")
              .withEmail("serdtimosh@gmail.com").withEmail2("serdtimosh+1@gmail.com").withEmail3("serdtimosh+2@gmail.com")
              .withHomePhone("+0472313950").withMobilePhone("+380945643839").withWorkPhone("+380975643850")
              .withGroup("test1").withPhoto(photo);
      app.contact().create(contact, true);
      Contacts after = app.contact().all();

      assertThat(after.size(), equalTo(before.size() + 1));
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
   }

  }
