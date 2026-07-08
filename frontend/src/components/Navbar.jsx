import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav
      style={{
        padding: "15px",
        backgroundColor: "#1976d2",
        display: "flex",
        gap: "20px",
      }}
    >
      <Link
        to="/"
        style={{ color: "white", textDecoration: "none", fontSize: "18px" }}
      >
        Dashboard
      </Link>

      <Link
        to="/projects"
        style={{ color: "white", textDecoration: "none", fontSize: "18px" }}
      >
        Projects
      </Link>

      <Link
        to="/add-task"
        style={{ color: "white", textDecoration: "none", fontSize: "18px" }}
      >
        Add Task
      </Link>

      <Link
        to="/tasks"
        style={{ color: "white", textDecoration: "none", fontSize: "18px" }}
      >
        Tasks
      </Link>
    </nav>
  );
}

export default Navbar;