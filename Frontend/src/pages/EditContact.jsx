import { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditContact() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address, setAddress] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`http://localhost:8080/Contact/getContactById/${id}`)
      .then((response) => response.json())
      .then((data) => {
        setName(data.name);
        setEmail(data.email);
        setPhoneNumber(data.phoneNumber);
        setAddress(data.address);
      });
  }, [id]);

  function updateContact() {
    const contact = {
      name: name,
      email: email,
      phoneNumber: phoneNumber,
      address: address,
    };

    fetch(`http://localhost:8080/Contact/updateContact/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(contact),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        navigate("/contacts");
      });
  }
  return (
    <div className="max-w-3xl mx-auto p-6">
      <div className="bg-white p-6 rounded-xl shadow-md">
        <h2 className="text-3xl font-bold text-center mb-6">Edit Student</h2>

        <div className="space-y-4">
          <div>
            <label className="block font-medium mb-2">Name</label>

            <input
              type="text"
              value={name}
              onChange={(event) => setName(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">Email</label>

            <input
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">PhoneNumber</label>

            <input
              type="text"
              value={phoneNumber}
              onChange={(event) => setPhoneNumber(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">Address</label>

            <input
              type="text"
              value={address}
              onChange={(event) => setAddress(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <button
            onClick={updateContact}
            className="w-full bg-green-600 text-white p-3 rounded-lg hover:bg-green-700 transition"
          >
            Update Contact
          </button>
        </div>
      </div>
    </div>
  );
}
export default EditContact;
