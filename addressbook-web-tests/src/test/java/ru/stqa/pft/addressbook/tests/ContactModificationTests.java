package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @Test
   public void testContactModification() {
      app.getNavigationHelper().goToHomePage();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Serhiy", "Tymoshenko", "17. Sichovy str.\n116 apt", "serdtimosh@gmail.com", "+0472313950", "+380945643839", "test1"), true);
      }
      List<ContactData> before = app.getContactHelper().getContactList();
      app.getContactHelper().initContactModification(before.size() - 1);
      ContactData group = new ContactData(before.get(before.size() - 1).getId(), "Vasyl", "Kosyi", "17. Sichovy str.\n118 apt", "serdtimosh@gmail.com", "+0472313945", "+380945643856", null);
      app.getContactHelper().fillContactForm(group, false);
      app.getContactHelper().submitContactModification();
      app.getContactHelper().returnToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size());


      before.remove(before.size() - 1);
      before.add(group);
      Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
   }
}
