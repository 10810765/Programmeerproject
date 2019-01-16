Basic rules (created with my team):

1. When an XML element doesn't have any contents, you **must** use self closing tags.
2. Resource IDs and names are written in **lowercase_underscore**.
3. Naming conventions for drawables:


| Asset Type   | Prefix          | Example                    |
| ------------ | --------------- | -------------------------- |
| Action bar   | `ab_`           | `ab_stacked.9.png`         |
| Button       | `btn_`          | `btn_send_pressed.9.png`   |
| Dialog       | `dialog_`       | `dialog_top.9.png`         |
| Divider      | `divider_`      | `divider_horizontal.9.png` |
| Icon         | `ic_`           | `ic_star.png`              |
| Menu         | `menu_	`        | `menu_submenu_bg.9.png`    |
| Notification | `notification_` | `notification_bg.9.png`    |
| Tabs         | `tab_`          | `tab_pressed.9.png`        |

4. When a method has many parameters or its parameters are very long, we should break the line after every comma

5. When the line is broken at an operator, the break comes __before__ the operator. For example:

   ```java
   int longName = anotherVeryLongVariable + anEvenLongerOne - thisRidiculousLongOne
           + theFinalOne;
   ```

6. It should all be in English and not in Dutch 

7. Appropriate comments: one-line comment, multiline comments and parameters when using classes.

8. Unlike the rest of resources, style names are written in **UpperCamelCase**.

9. Don't use different disturbing colors in one View

10. Separate processing form the UI Activity
