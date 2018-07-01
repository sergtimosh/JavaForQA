package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

   public ContactHelper(WebDriver wd) {
      super(wd);
   }

   public void initContactCreation() {
      click(By.linkText("add new"));
   }

   public void fillContactForm(ContactData contactData, boolean creation) {
      type(By.name("firstname"), contactData.getFirstname());
      type(By.name("lastname"), contactData.getSecondname());
      type(By.name("address"), contactData.getAddress());
      type(By.name("email"), contactData.getEmail());
      type(By.name("home"), contactData.getPhone1());
      type(By.name("mobile"), contactData.getPhone2());

      if (creation) {
         new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
      } else {
         Assert.assertFalse(isElementPresent(By.name("new_group")));
      }


   }

   public void selectContact() {
      click(By.name("selected[]"));
   }

   public void deleteSelectedContact() {
      click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
   }

   public void submitContactCreation() {
      click(By.xpath("//div[@id='content']/form/input[21]"));
   }

   public void acceptContactDeletion() {
      wd.switchTo().alert().accept();
   }

   public void initContactModification() {
      click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
   }

   public void submitContactModification() {
      click(By.xpath("//div[@id='content']/form[1]/input[22]"));
   }

   public void returnToHomePage() {
      click(By.linkText("home page"));
   }

   public void createContact(ContactData contact, boolean creation) {
      initContactCreation();
      fillContactForm(contact, creation);
      submitContactCreation();
      returnToHomePage();
   }

   public boolean isThereAContact() {
      return isElementPresent(By.name("selected[]"));
   }

   public int getContactCount() {
      return wd.findElements(By.name("selected[]")).size();
   }

   public List<ContactData> getContactList() {
      List<ContactData> contacts = new ArrayList<>();
      List<WebElement> elements = wd.findElements(By.cssSelector("[name = entry]"));
      for (WebElement element : elements) {
         String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
         String s_name = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
         ContactData contact = new ContactData(name, s_name, null, null, null, null, null);
         contacts.add(contact);
      }
      return contacts;
   }
}