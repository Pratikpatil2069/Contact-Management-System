import { useState } from "react";
import { useNavigate } from "react-router-dom";

function AddContact() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address, setAddress] = useState("");

  const navigate = useNavigate();

  function addContact() {
    const contact = {
      name: name,
      email: email,
      phoneNumber: phoneNumber,
      address: address,
    };

    fetch("http://localhost:8080/Contact/addContact", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(contact),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        setName("");
        setEmail("");
        setPhoneNumber("");
        setAddress("");
        navigate("/contacts");
      });
  }
  return (
    <div className="max-w-3xl mx-auto p-6">
      <div className="bg-white p-6 rounded-xl shadow-md">
        <h2 className="text-3xl font-bold mb-6 text-center">Add Contact</h2>
        <div className="space-y-4">
          <div>
            <label className="block font-medium mb-2">Name</label>
            <input
              type="text"
              placeholder="Enter the Name"
              value={name}
              onChange={(event) => setName(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">Email</label>
            <input
              type="email"
              placeholder="Enter the Email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">Phone Number</label>
            <input
              type="text"
              placeholder="Enter the PhoneNumber"
              value={phoneNumber}
              onChange={(event) => setPhoneNumber(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block font-medium mb-2">Address</label>
            <input
              type="text"
              placeholder="Enter the Address"
              value={address}
              onChange={(event) => setAddress(event.target.value)}
              className="w-full border rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <button
            onClick={addContact}
            className="w-full bg-blue-600 text-white p-3 rounded-lg hover:bg-blue-700 hover:scale-[1.01] transition duration-300"
          >
            Add Contact
          </button>
        </div>
      </div>
    </div>
  );
}
export default AddContact;
