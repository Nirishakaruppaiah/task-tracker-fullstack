import { useEffect, useState } from "react";
import axios from "axios";

function Dashboard() {
  const [taskCount, setTaskCount] = useState(0);
  const [projectCount, setProjectCount] = useState(0);
  const [completedCount, setCompletedCount] = useState(0);
  const [pendingCount, setPendingCount] = useState(0);

  useEffect(() => {
    loadDashboard();
  }, []);

  const loadDashboard = async () => {
    try {
      const taskResponse = await axios.get("http://localhost:8080/tasks");
      const projectResponse = await axios.get("http://localhost:8080/projects");

      const tasks = taskResponse.data.content || taskResponse.data;
      const projects = projectResponse.data;

      setTaskCount(tasks.length);
      setProjectCount(projects.length);

      setCompletedCount(
        tasks.filter((task) => task.status === "DONE").length
      );

      setPendingCount(
        tasks.filter((task) => task.status !== "DONE").length
      );
    } catch (error) {
      console.error(error);
    }
  };

  const cardStyle = {
    background: "#1976d2",
    color: "white",
    padding: "20px",
    borderRadius: "10px",
    width: "220px",
    textAlign: "center",
    boxShadow: "0 3px 10px rgba(0,0,0,0.3)",
  };

  return (
    <div style={{ padding: "30px" }}>
      <h1>Task Tracker Dashboard</h1>

      <p>Welcome to your Task Tracker application.</p>

      <div
        style={{
          display: "flex",
          gap: "20px",
          flexWrap: "wrap",
          marginTop: "30px",
        }}
      >
        <div style={cardStyle}>
          <h2>{taskCount}</h2>
          <p>Total Tasks</p>
        </div>

        <div style={cardStyle}>
          <h2>{projectCount}</h2>
          <p>Total Projects</p>
        </div>

        <div style={cardStyle}>
          <h2>{completedCount}</h2>
          <p>Completed Tasks</p>
        </div>

        <div style={cardStyle}>
          <h2>{pendingCount}</h2>
          <p>Pending Tasks</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;