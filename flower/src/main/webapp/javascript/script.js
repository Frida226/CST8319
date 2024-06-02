/**
 * 
 */
window.onload = function() {
            showRegisterSuccess();
        };
        
function showRegisterSuccess() {
    var status = document.getElementById("status").value;
    if (status === "success") {
        alert('Account Created Successfully!');
        return true;
    } else {
        return false;
    }
}

window.onload = function() {
    showLoginMessage();
};

function showLoginMessage() {
    var loginStatus  = document.getElementById("status").value;
    if (loginStatus === "failed") {
        alert('Sorry, wrong username or password.');
    }
}