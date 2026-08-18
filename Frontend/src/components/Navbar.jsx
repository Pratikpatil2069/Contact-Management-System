import { Link } from "react-router-dom";
function Navbar() {
  return (
    <nav className="bg-blue-600 text-white shadow-md">
      <div className="max-w-6xl mx-auto flex justify-between items-center p-4">
        <h1 className="text-2xl font-bold">ContactApp</h1>
        <div className="flex gap-6">
          <Link to="/" className="hover:text-gray-200">
            Home
          </Link>

          <Link to="/add-contact" className="hover:text-gray-200">
            Add Contact
          </Link>

          <Link to="/contacts" className="hover:text-gray-200">
            Contact List
          </Link>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
