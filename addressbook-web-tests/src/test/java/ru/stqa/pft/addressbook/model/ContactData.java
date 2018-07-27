package ru.stqa.pft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
public class ContactData {

   @Id
   @Column(name = "id")
   private int id = Integer.MAX_VALUE;

   @Column(name = "firstname")
   private String firstName;

   @Column(name = "lastname")
   private String lastName;

   @Column(name = "address")
   @Type(type = "text")
   private String address;

   @Column(name = "home")
   @Type(type = "text")
   private String homePhone;

   @Column(name = "mobile")
   @Type(type = "text")
   private String mobilePhone;

   @Column(name = "work")
   @Type(type = "text")
   private String workPhone;

   @Transient
   private String allPhones;

   @Column(name = "email")
   @Type(type = "text")
   private String email;

   @Column(name = "email2")
   @Type(type = "text")
   private String email2;

   @Column(name = "email3")
   @Type(type = "text")
   private String email3;

   @Transient
   private String allEmails;

   @Transient
   private String group;

   @Column(name = "photo")
   @Type(type = "text")
   private String photo;


   public ContactData() {
   }

   public int getId() {
      return id;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getSecondName() {
      return lastName;
   }

   public String getAddress() {
      return address;
   }

   public String getEmail() {
      return email;
   }

   public String getEmail2() {
      return email2;
   }

   public String getEmail3() {
      return email3;
   }

   public String getAllEmails() {
      return allEmails;
   }

   public String getHomePhone() {
      return homePhone;
   }

   public String getMobilePhone() {
      return mobilePhone;
   }

   public String getWorkPhone() {
      return workPhone;
   }

   public String getAllPhones() {
      return allPhones;
   }

   public String getGroup() {
      return group;
   }

   public File getPhoto() {
      return new File(photo);
   }


   public ContactData withId(int id) {
      this.id = id;
      return this;
   }

   public ContactData withFirstName(String firstName) {
      this.firstName = firstName;
      return this;
   }

   public ContactData withLastName(String secondName) {
      this.lastName = secondName;
      return this;
   }

   public ContactData withAddress(String address) {
      this.address = address;
      return this;
   }

   public ContactData withEmail(String email) {
      this.email = email;
      return this;
   }

   public ContactData withEmail2(String email2) {
      this.email2 = email2;
      return this;
   }

   public ContactData withEmail3(String email3) {
      this.email3 = email3;
      return this;
   }

   public ContactData withAllEmails(String allEmails) {
      this.allEmails = allEmails;
      return this;
   }

   public ContactData withHomePhone(String phone1) {
      this.homePhone = phone1;
      return this;
   }

   public ContactData withMobilePhone(String phone2) {
      this.mobilePhone = phone2;
      return this;
   }

   public ContactData withWorkPhone(String phone3) {
      this.workPhone = phone3;
      return this;
   }

   public ContactData withAllPhones(String allPhones) {
      this.allPhones = allPhones;
      return this;
   }

   public ContactData withGroup(String group) {
      this.group = group;
      return this;
   }

   public ContactData withPhoto(File photo) {
      this.photo = photo.getPath();
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ContactData)) return false;
      ContactData that = (ContactData) o;
      return id == that.id &&
              Objects.equals(firstName, that.firstName) &&
              Objects.equals(lastName, that.lastName) &&
              Objects.equals(address, that.address) &&
              Objects.equals(homePhone, that.homePhone) &&
              Objects.equals(mobilePhone, that.mobilePhone) &&
              Objects.equals(workPhone, that.workPhone) &&
              Objects.equals(email, that.email) &&
              Objects.equals(email2, that.email2) &&
              Objects.equals(email3, that.email3);
   }

   @Override
   public int hashCode() {

      return Objects.hash(id, firstName, lastName, address, homePhone, mobilePhone, workPhone, email, email2, email3);
   }

   @Override
   public String toString() {
      return "ContactData{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", address='" + address + '\'' +
              ", homePhone='" + homePhone + '\'' +
              ", mobilePhone='" + mobilePhone + '\'' +
              ", workPhone='" + workPhone + '\'' +
              ", email='" + email + '\'' +
              ", email2='" + email2 + '\'' +
              ", email3='" + email3 + '\'' +
              ", group='" + group + '\'' +
              '}';
   }

}
