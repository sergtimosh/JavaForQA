package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

   @Parameter(names = "-c", description = "Contact count")
   public int count;

   @Parameter(names = "-f", description = "Target file")
   public String file;

   @Parameter(names = "-d", description = "Data format")
   public String format;

   public static void main(String[] args) throws IOException {
      ContactDataGenerator generator = new ContactDataGenerator();
      JCommander jCommander = new JCommander(generator);
      try {
         jCommander.parse(args);
      } catch (ParameterException ex) {
         jCommander.usage();
         return;
      }
      generator.run();
   }

   private void run() throws IOException {
      List<ContactData> contacts = generateContacts(count);
      if (format.equals("json")) {
         saveAsJson(contacts, new File(file));
      } else {
         System.out.println("Unrecognized format " + format);
      }
   }

   private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
      Gson gson = new GsonBuilder().setPrettyPrinting().setExclusionStrategies(new ExclusionStrategies()).create();
      String json = gson.toJson(contacts);
      Writer writer = new FileWriter(file);
      writer.write(json);
      writer.close();
   }

   private List<ContactData> generateContacts(int count) {
      List<ContactData> contacts = new ArrayList<ContactData>();
      File photo = new File("src/test/resources/duck.png");

      for (int i = 0; i < count; i++) {
         contacts.add(new ContactData()
                 .withFirstName(String.format("Serhiy %s", i)).withLastName(String.format("Tymoshenko %s", i))
                 .withAddress(String.format("1%s. Sichovy str.\n11%s apt %s", i))
                 .withHomePhone(String.format("+047231395%s", i)).withMobilePhone(String.format("+047231395%s", i)).withWorkPhone(String.format("+047231395%s", i))
                 .withEmail(String.format("serdtimosh%s@gmail.com", i)).withEmail2(String.format("serdtimosh1%s@gmail.com", i)).withEmail3(String.format("serdtimosh3%s@gmail.com", i))
                 .withGroup(String.format("test %s", i))
                 .withPhoto(photo));
      }
      return contacts;
   }


}
