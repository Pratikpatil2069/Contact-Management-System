import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import AddContact from "./pages/addContact";
import EditContact from "./pages/EditContact";
import ContactList from "./pages/ContactList";
function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-Contact" element={<AddContact />} />
        <Route path="/edit-Contact/:id" element={<EditContact />} />
        <Route path="/contacts" element={<ContactList />}></Route>
      </Routes>
    </>
  );
}
export default App;
