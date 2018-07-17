package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

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
   public void testContactModification() {
      Set<ContactData> before = app.contact().all();
      ContactData modifiedContact = before.iterator().next();
      ContactData contact = new ContactData()
              .withId(modifiedContact.getId())
              .withFirstName("Vasyl")
              .withSecondName("Kosyi")
              .withAddress("17. Sichovy str.\n118 apt")
              .withEmail("serdtimosh@gmail.com")
              .withPhone1("+0472313945")
              .withPhone2("+380945643856")
              .withGroup(null);
      app.contact().modify(contact);
      Set<ContactData> after = app.contact().all();
      Assert.assertEquals(after.size(), before.size());

      before.remove(modifiedContact);
      before.add(contact);
      Assert.assertEquals(before, after);
   }

}
