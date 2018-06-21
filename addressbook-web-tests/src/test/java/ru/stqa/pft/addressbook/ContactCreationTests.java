package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.initContactCreation();
    app.fillContactForm(new ContactData("Serhiy", "Tymoshenko", "17. Sichovy str.\n116 apt", "serdtimosh@gmail.com", "+0472313950", "+380945643839"));
    app.submitContactCreation();
    app.goToHomePage();

  }

}
