function navigate(path) {
    // Adjust base path to match your Tomcat deployment
    const base = window.location.origin + "/StudentManagementSystem";
    window.location.href = base + path;
}