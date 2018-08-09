package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

   public NavigationHelper(ApplicationManager app) {
      super(app);
   }

   public void managePage() {
      if (isElementPresent(By.cssSelector("a[href = 'manage_overview_page.php'"))) {
         return;
      }
      click(By.linkText("Manage"));
   }

   public void HomePage() {
      if (isElementPresent(By.id("maintable"))) {
         return;
      }
      click(By.linkText("home"));
   }

   public void manageUsersPage() {
      if (isElementPresent(By.linkText("Manage Accounts"))) {
         return;
      }
      click(By.linkText("Manage Users"));
   }
}
