package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

   private final String id;
   private final String firstname;
   private final String secondname;
   private final String address;
   private final String email;
   private final String phone1;
   private final String phone2;
   private String group;

   public ContactData(String firstname, String secondName, String address, String email, String phone1, String phone2, String group) {
      this.id = null;
      this.firstname = firstname;
      this.secondname = secondName;
      this.address = address;
      this.email = email;
      this.phone1 = phone1;
      this.phone2 = phone2;
      this.group = group;
   }
   public ContactData(String id, String firstname, String secondname, String address, String email, String phone1, String phone2, String group) {
      this.id = id;
      this.firstname = firstname;
      this.secondname = secondname;
      this.address = address;
      this.email = email;
      this.phone1 = phone1;
      this.phone2 = phone2;
      this.group = group;
   }

   public String getId() {
      return id;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getSecondname() {
      return secondname;
   }

   public String getAddress() {
      return address;
   }

   public String getEmail() {
      return email;
   }

   public String getPhone1() {
      return phone1;
   }

   public String getPhone2() {
      return phone2;
   }

   public String getGroup() {
      return group;
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id='" + id + '\'' +
              ", firstname='" + firstname + '\'' +
              ", secondname='" + secondname + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      ContactData that = (ContactData) o;
      return Objects.equals(id, that.id) &&
              Objects.equals(firstname, that.firstname) &&
              Objects.equals(secondname, that.secondname);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstname, secondname);
   }
}
