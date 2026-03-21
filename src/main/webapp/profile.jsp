<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <style>
        body { margin:0; font-family: Arial, sans-serif; background:#f4f6f9; }
        header {
            background:#2196F3; color:white; padding:15px;
            text-align:center; font-size:26px; font-weight:bold;
        }
        footer {
            background:#333; color:white; text-align:center;
            padding:10px; position:fixed; bottom:0; width:100%;
        }
        .sidebar {
            width:220px; background:#2c3e50; color:white;
            position:fixed; top:60px; bottom:40px; left:0;
            padding-top:20px;
        }
        .sidebar a {
            display:block; color:white; padding:12px 20px;
            text-decoration:none; transition:0.3s;
        }
        .sidebar a:hover { background:#34495e; }
        .main {
            margin-left:240px; padding:20px;
        }
        .profile-card {
            background:#fff; border-radius:12px; padding:30px;
            box-shadow:0 4px 15px rgba(0,0,0,0.1);
            text-align:center; max-width:500px; margin:auto;
        }
        .profile-img {
            width:120px; height:120px; border-radius:50%;
            object-fit:cover; margin-bottom:15px;
            border:3px solid #2196F3;
        }
        h2 { margin:10px 0; color:#333; }
        p { margin:5px 0; color:#555; }

        /* Colorful info blocks */
        .info-blocks {
            display:flex; justify-content:space-around; margin-top:30px;
        }
        .block {
            flex:1; margin:10px; padding:20px;
            border-radius:10px; color:white; font-weight:bold;
            text-align:center; box-shadow:0 3px 10px rgba(0,0,0,0.1);
        }
        .block1 { background:#e74c3c; }
        .block2 { background:#27ae60; }
        .block3 { background:#f39c12; }
    </style>
</head>
<body>
    <!-- Header -->
    <header>My College - Student Dashboard</header>

    <!-- Sidebar -->
    <div class="sidebar">
        <a href="studentDashboard.jsp">Dashboard</a>
        <a href="profile.jsp">Profile</a>
        <a href="register.html">Register Student</a>
        <a href="ViewStudentsServlet">View Students</a>
        <a href="UpdateServlet">Update Student</a>
        <a href="DeleteServlet">Delete Student</a>
        <a href="logout.jsp">Logout</a>
    </div>

    <!-- Main Content -->
    <div class="main">
        <!-- Profile Card -->
        <div class="profile-card">
            <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" alt="Profile Image" class="profile-img">
            <h2><%= session.getAttribute("name") %></h2>
            <p>Roll No: <%= session.getAttribute("roll") %></p>
            <p>Email: <%= session.getAttribute("email") %></p>
            <p>Phone: <%= session.getAttribute("phone") %></p>
            <p>Marks: <%= session.getAttribute("mark") %></p>
        </div>

        <!-- Colorful Info Blocks -->
        <div class="info-blocks">
            <div class="block block1">Upcoming Exams</div>
            <div class="block block2">Library Access</div>
            <div class="block block3">Sports & Activities</div>
        </div>
    </div>

    <!-- Footer -->
    <footer>&copy; 2026 My College. All rights reserved.</footer>
</body>
</html>