package ru.stqa.pft.addressbook.generators;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ExclusionStrategies implements ExclusionStrategy {

   public boolean shouldSkipField(FieldAttributes f) {
      return (f.getDeclaringClass() == ContactData.class && f.getName().equals("id"))
              || (f.getDeclaringClass() == GroupData.class && f.getName().equals("id"));

   }

   @Override
   public boolean shouldSkipClass(Class<?> clazz) {
      return false;
   }
}
