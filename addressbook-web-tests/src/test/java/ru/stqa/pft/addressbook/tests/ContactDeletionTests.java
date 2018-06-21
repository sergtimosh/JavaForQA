package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void ContactDeletionTests() {

    app.goToHomePage();
    app.selectContact();
    app.deleteSelectedContact();
    app.acceptContactDeletion();
    app.goToHomePage();

  }

}