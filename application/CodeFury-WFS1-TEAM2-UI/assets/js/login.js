// Get DOM elements
const signUpBtn = document.getElementById('signUp');
const signInBtn = document.getElementById('signInSubmit');
const adminSignInBtn = document.getElementById('adminSignIn');
const container = document.getElementById('container');
const signUpForm = document.getElementById('signUpForm');
const signInForm = document.getElementById('signInForm');
const adminSignInForm = document.getElementById('adminSignInForm');
const signInBack= document.getElementById('signInBack');


// Add event listeners for panel switching
signUpBtn.addEventListener('click', () => {
    container.classList.add('right-panel-active');
    container.classList.remove('right-panel-active-admin');
});

signInBtn.addEventListener('click', () => {
    const email= document.getElementById('signInEmail').value;
    const pass= document.getElementById('signInPassword').value;
    console.log(email)
    if(validateEmail(email) && pass.length>=8){
        container.classList.remove('right-panel-active');
        container.classList.remove('right-panel-active-admin');
        window.location='../home.html'
    }
   
    else if(validateEmail(email)==false){
       alert("EMAIL VALIDATION FAILED") 
    }
    else{
        alert("Password length is too small")
    }

    
    
});


signInBack.addEventListener('click',()=>{
  
    //container.classList.add('right-panel-active')
    container.classList.remove('right-panel-active');
    container.classList.remove('right-panel-active-admin');
   // container.classList.add('left-panel-active');
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
    

// Admin Sign In form validation
adminSignInForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const adminID = document.getElementById('adminSignInID').value;
    const password = document.getElementById('adminSignInPassword').value;
    const submitbutton = document.getElementById('adminSignInSubmit');



    if (!adminID || !password) {
        alert('Please fill in all fields.');
        return;
    }

    if (adminID === "admin" && password === "admin") {

        window.location.href = "../pages/adminSubsc.html";

    }

    // Perform additional validation or submit the form
    console.log('Admin Sign In form submitted successfully.');
});

// Email validation function
function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}
