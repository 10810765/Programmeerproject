Basic rules (created with my team):

1. When an XML element doesn't have any contents, you **must** use self closing tags.
2. Resource IDs and names are written in **camelCase**.
3. Use clear drawable names written in **lowercase_underscore**.
4. When a method has many parameters or its parameters are very long, we should break the line after every comma

5. When the line is broken at an operator, the break comes __before__ the operator. For example:

   ```java
   int longName = anotherVeryLongVariable + anEvenLongerOne - thisRidiculousLongOne
           + theFinalOne;
   ```

6. Code and commets should be written in English and not in Dutch 

7. Appropriate comments: one-line comment, multiline comments and parameters when using classes.

8. Unlike the rest of resources, style names are written in **camelCase**.

9. Don't use different disturbing colors in one View

10. Separate processing form the UI Activity
