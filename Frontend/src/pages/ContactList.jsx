import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function ContactList() {
  const [Contacts, setContacts] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    getAllContacts();
  }, []);

  function getAllContacts() {
    fetch("http://localhost:8080/Contact/getAllContacts")
      .then((response) => response.json())
      .then((data) => {
        setContacts(data);
      });
  }

  function deleteContact(id) {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this Contact?",
    );

    if (!confirmDelete) {
      return;
    }

    fetch(`http://localhost:8080/Contact/deleteContact/${id}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        getAllContacts();
      });
  }

  function editContact(id) {
    navigate(`/edit-Contact/${id}`);
  }

  return (
    <div className="max-w-6xl mx-auto p-6">
      <div className="bg-white p-6 rounded-xl shadow-md">
        <h2 className="text-3xl font-bold mb-6 text-center">Contact List</h2>

        <div className="overflow-hidden rounded-lg border border-gray-200">
          <table className="w-full border-collapse">
            <thead>
              <tr className="bg-blue-600 text-white">
                <th className="border p-3 text-left">Name</th>
                <th className="border p-3 text-left">Email</th>
                <th className="border p-3 text-left">PhoneNumber</th>
                <th className="border p-3 text-left">Address</th>
                <th className="border p-3 text-left">Actions</th>
              </tr>
            </thead>

            <tbody>
              {Contacts.map((contact) => (
                <tr
                  key={contact.id}
                  className="hover:bg-gray-50 even:bg-gray-50"
                >
                  <td className="border p-3">{contact.name}</td>

                  <td className="border p-3">{contact.email}</td>

                  <td className="border p-3">{contact.phoneNumber}</td>

                  <td className="border p-3">{contact.address}</td>

                  <td className="border p-3">
                    <button
                      onClick={() => editContact(contact.id)}
                      className="bg-green-500 text-white px-3 py-1 rounded-lg mr-2 hover:bg-green-600 transition"
                    >
                      Update
                    </button>

                    <button
                      onClick={() => deleteContact(contact.id)}
                      className="bg-red-500 text-white px-3 py-1 rounded-lg hover:bg-red-600 transition"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default ContactList;
