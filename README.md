 📇 Kontakt Manager

A desktop application built with Java Swing that allows you to manage a contact list. You can add, modify, delete, and filter contacts with ease through an intuitive GUI.

---

## 🚀 Features

- 📝 Add new contacts with fields like:
  - Name / Vorname
  - Company (Unternehmen)
  - Email
  - Phone number
  - Contact type (Enum: Kunde, Lieferant, Unbekannt)
  - Favorite status ⭐

- 🔍 Filter contacts by:
  - Type, Name, Vorname, Company, Email, Phone, Favorites

- 🛠 Modify existing contacts with data pre-filled in the form

- 🗑 Delete selected contact directly with **Delete** key

- 🎹 Keyboard Shortcuts:
  - `Entf` (Delete) → Delete selected contact
  - `Enter` → Apply filter
  - `Esc` → Clear filter

- 💾 Confirm save on exit, with prompt to store changes to the database

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
- `NeuKontakt.java`: GUI for adding a new contact
- `ModKontakt.java`: GUI for modifying existing contact
- `Kontakt.java`: Data class for contact information
- `KontaktTableModel.java`: Custom `TableModel` to manage and refresh data
- `BaseManager.java`: Handles data persistence (load/save)

---

## 🧪 Validation

- Input validation for:
  - Non-empty critical fields
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
- Dark mode toggle 🌙

---

## 🧑‍💻 Author

Developed by German Hoffmann 🇩🇪🇦🇷  
Passionate about software development, learning Java and building desktop apps.

---

## 📜 License

MIT License – see [LICENSE](LICENSE) for details.
