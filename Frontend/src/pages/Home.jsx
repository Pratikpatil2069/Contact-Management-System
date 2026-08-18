import { Link } from "react-router-dom";
import { useEffect, useState } from "react";

function Home() {
  const [contacts, setContacts] = useState([]);
  useEffect(() => {
    fetch("http://localhost:8080/Contact/getAllContacts")
      .then((response) => response.json())
      .then((data) => {
        setContacts(data);
      });
  }, []);
  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-4xl font-bold text-center mb-10">
        Contact Management System
      </h1>

      <div className="grid md:grid-cols-2 gap-6 mb-10">
        <Link to="/add-contact">
          <div className="bg-blue-600 text-white p-8 rounded-xl shadow-md hover:scale-105 transition">
            <h2 className="text-2xl font-bold mb-2">Add Contact</h2>
            <p>Add new contacts to the system.</p>
          </div>
        </Link>

        <Link to="/contacts">
          <div className="bg-green-600 text-white p-8 rounded-xl shadow-md hover:scale-105 transition">
            <h2 className="text-2xl font-bold mb-2">View Contacts</h2>
            <p>View, update and delete contacts.</p>
          </div>
        </Link>
      </div>

      <div className="bg-white rounded-xl shadow-md p-6">
        <h2 className="text-2xl font-semibold mb-4">Recent Contacts</h2>
        {contacts.slice(0, 5).map((contact) => (
          <div key={contact.id} className="border-b py-2">
            <p className="font-medium">{contact.name}</p>
            <p className="text-gray-500 text-sm">{contact.email}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;
