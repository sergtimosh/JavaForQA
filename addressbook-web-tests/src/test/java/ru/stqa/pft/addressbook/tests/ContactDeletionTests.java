package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

   @Test
   public void ContactDeletionTests() {
      app.getNavigationHelper().goToHomePage();
      List<ContactData> before = app.getContactHelper().getContactList();
      if (!app.getContactHelper().isThereAContact()) {
         app.getContactHelper().createContact(new ContactData("Serhiy", "Tymoshenko", "17. Sichovy str.\n116 apt", "serdtimosh@gmail.com", "+0472313950", "+380945643839", "test1"), true);
      }
      app.getContactHelper().selectContact(before.size() - 1);
      app.getContactHelper().deleteSelectedContact();
      app.getContactHelper().acceptContactDeletion();
      app.getNavigationHelper().goToHomePage();
      List<ContactData> after = app.getContactHelper().getContactList();
      Assert.assertEquals(after.size(), before.size() - 1);

      before.remove(before.size() - 1);
      Assert.assertEquals(before, after);

   }

}