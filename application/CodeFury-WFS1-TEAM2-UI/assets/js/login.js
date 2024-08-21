// Get DOM elements
const signUpBtn = document.getElementById('signUp');
const signInBtn = document.getElementById('signIn');
const adminSignInBtn = document.getElementById('adminSignIn');
const container = document.getElementById('container');
const signUpForm = document.getElementById('signUpForm');
const signInForm = document.getElementById('signInForm');
const adminSignInForm = document.getElementById('adminSignInForm');

// Add event listeners for panel switching
signUpBtn.addEventListener('click', () => {
    container.classList.add('right-panel-active');
    container.classList.remove('right-panel-active-admin');
});

signInBtn.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
    container.classList.remove('right-panel-active-admin');
});

adminSignInBtn.addEventListener('click', () => {
    container.classList.add('right-panel-active-admin');
    container.classList.remove('right-panel-active');
});

// Sign Up form validation
signUpForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = document.getElementById('signUpName').value;
    const email = document.getElementById('signUpEmail').value;
    const password = document.getElementById('signUpPassword').value;

    if (!name || !email || !password) {
        alert('Please fill in all fields.');
        return;
    }

    if (!validateEmail(email)) {
        alert('Please enter a valid email address.');
        return;
    }

    if (password.length < 8) {
        alert('Password must be at least 8 characters long.');
        return;
    }

    // Perform additional validation or submit the form
    console.log('Sign Up form submitted successfully.');
});

// Sign In form validation
signInForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const email = document.getElementById('signInEmail').value;
    const password = document.getElementById('signInPassword').value;

    if (!email || !password) {
        alert('Please fill in all fields.');
        return;
    }

    if (!validateEmail(email)) {
        alert('Please enter a valid email address.');
        return;
    }

    // Perform additional validation or submit the form
    console.log('Sign In form submitted successfully.');
});

// Admin Sign In form validation
adminSignInForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const adminID = document.getElementById('adminSignInID').value;
    const password = document.getElementById('adminSignInPassword').value;

    if (!adminID || !password) {
        alert('Please fill in all fields.');
        return;
    }

    // Perform additional validation or submit the form
    console.log('Admin Sign In form submitted successfully.');
});

// Email validation function
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}
