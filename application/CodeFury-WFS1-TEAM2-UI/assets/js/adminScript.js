function toggleContent(activeId, inactiveId, activeButton, inactiveButton) {
    document.getElementById(activeId).classList.add('active');
    document.getElementById(inactiveId).classList.remove('active');
    document.getElementById(activeButton).classList.add('active');
    document.getElementById(inactiveButton).classList.remove('active');
}

// Function to handle subscription button visibility
function showSubscriptionButtons(show) {
    document.querySelector('.subscription-buttons').style.display = show ? 'block' : 'none';
}

// Set up event listeners for the buttons
document.getElementById('manageProductsBtn').addEventListener('click', function() {
    toggleContent('product-main', 'product-minimal', 'manageProductsBtn', 'manageSubscriptionBtn');
    showSubscriptionButtons(false); // Hide subscription buttons when managing products
});

document.getElementById('manageSubscriptionBtn').addEventListener('click', function() {
    toggleContent('product-minimal', 'product-main', 'manageSubscriptionBtn', 'manageProductsBtn');
    showSubscriptionButtons(true); // Show subscription buttons when managing subscriptions
});

// Subscription button event listeners
document.getElementById('dailyBtn').addEventListener('click', function() {
    document.getElementById('dailyContent').classList.add('active');
    document.getElementById('weeklyContent').classList.remove('active');
    document.getElementById('monthlyContent').classList.remove('active');
});

document.getElementById('weeklyBtn').addEventListener('click', function() {
    document.getElementById('weeklyContent').classList.add('active');
    document.getElementById('dailyContent').classList.remove('active');
    document.getElementById('monthlyContent').classList.remove('active');
});

document.getElementById('monthlyBtn').addEventListener('click', function() {
    document.getElementById('monthlyContent').classList.add('active');
    document.getElementById('dailyContent').classList.remove('active');
    document.getElementById('weeklyContent').classList.remove('active');
});


document.querySelectorAll('.subscription-buttons button').forEach(button => {
    button.addEventListener('click', function() {
        document.querySelectorAll('.subscription-buttons button').forEach(btn => {
            btn.classList.remove('active');
        });
        this.classList.add('active');
    });
});