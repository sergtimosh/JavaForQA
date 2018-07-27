package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTests extends TestBase {

   public static String cleaned(String contactData) {
      return contactData.replaceAll("\\s", "").replaceAll("[-()]]", "");
   }

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().HomePage();
      if (app.contact().all().size() == 0) {
         app.contact().create(new ContactData()
                 .withFirstName("Serhiy").withLastName("Tymoshenko")
                 .withAddress("17. Sichovy str.\n116 apt")
                 .withEmail("serdtimosh@gmail.com").withEmail2("serdtimosh+1@gmail.com").withEmail3("serdtimosh+2@gmail.com")
                 .withHomePhone("+0472313950").withMobilePhone("+380945643839").withWorkPhone("+380975643850")
                 .withGroup("test1"), true);
      }
   }

   /**
    * Tests, to verify, that contact data from home page
    * is equal to corresponding contact data from contact modification page.
    * Every single test verifies separate data column(ex. Address, All e-mail etc.)
    */
   @Test
   public void testContactPhones() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
   }

   @Test
   public void testContactAddress() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(cleaned(contact.getAddress()), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
   }

   @Test
   public void testContactEmails() {
      app.goTo().HomePage();
      ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
   }

   private String mergePhones(ContactData contact) {
      return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactDataTests::cleaned)
              .collect(Collectors.joining("\n"));
   }

   private String mergeEmails(ContactData contact) {
      return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
              .stream().filter((s) -> !s.equals(""))
              .map(ContactDataTests::cleaned)
              .collect(Collectors.joining("\n"));
   }
}
