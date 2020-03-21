<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<body>
    <h2>Error Page</h2>
    <p>Something went wrong...</p>
    <div>Status code: <b>${status}</b></div>
    <div>Exception Message: <b>${msg}</b></div>
    <p></p>
    <div>
        <p>Return to <a href="/api/admin">Admin Dashboard</a></p>
    </div>
        <p></p>
</body>

</html>