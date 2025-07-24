 📇 Kontakt Manager

A desktop application built with Java Swing that allows you to manage a contact list. You can add, modify, delete, and filter contacts with ease through an intuitive GUI.

---

## 🚀 Features

- 📝 Add new contacts with fields like:
  - Name(Lastname) / Vorname(First Name)
  - Company (Unternehmen)
  - Adresse (Address)
  - PLZ (ZIP Code)
  - Stadt (City)
  - Email
  - Phone number
  - Contact type (Enum: Kunde, Lieferant, Unbekannt)
  - Favorite status ⭐

- 🔍 Filter contacts by:
  - Type, Name, First Name, Company, Address, ZIP Code, City, Email, Phone, Favorites
- 🛠 Modify existing contacts with data pre-filled in the form
- 🗑 Delete selected contact directly with **Delete** key
- Dark mode toggle 🌙
- 🎹 Keyboard Shortcuts:
  - `Entf` (Delete) → Delete selected contact
  - `Enter` → Apply filter
  - `Esc` → Clear filter

- 💾 Confirm save on exit, with prompt to store changes to the database
- 💾 Back-Up function

---

## 🖼 User Interface

Built entirely with `Java Swing`, using:
- `JFrame`, `JTable`, `JComboBox`, `JScrollPane`, `JOptionPane`, etc.
- `GridBagLayout` for flexible GUI design
- `TableRowSorter` for filter functionality
- Custom colors and layout for modern feel

---

## 📁 Structure

- `MainGUI.java`: Main program window and logic
- `Neu_Mod_Kontakt.java`: GUI for adding a new contact and modify a existent
- `Kontakt.java`: Data class for contact information
- `KontaktTableModel.java`: Custom `TableModel` to manage and refresh data
- `BaseManager.java`: Handles data persistence (load/save)
- `ThemeManager.java`: Handles Light/Dark Theme
- `MusterKontakte.java`: Random Contact Generator

---

## 🧪 Data Validation

- Input validation for:
  - Non-empty critical fields
  - Comma avoiding in Fields
  - Email format
  - Phone number format (digits, optional `+`, spaces)

---

## 🛠 Requirements

- Java 8 or higher
- Any Java IDE (IntelliJ, Eclipse, VS Code, etc.)

---

## ✅ TODO / Future Improvements

- Export/Import contacts to CSV or JSON
- Persistent database using SQLite or file-based storage
- Sortable table columns
- Better error messages and form UX
- Language support (English, Spanish)


---

## 🧑‍💻 Author

Developed by German Hoffmann 🇩🇪🇦🇷  
Passionate about software development, learning Java and building desktop apps.

---

## 📜 License

MIT License – see [LICENSE](LICENSE) for details.
