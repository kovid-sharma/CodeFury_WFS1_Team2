// Sample order data
const orders = [
    {
        id: 'ORDER12345',
        productName: 'Wireless Headphones',
        imageUrl: 'https://via.placeholder.com/100',
        quantity: 1,
        price: 99.99,
        status: 'completed',
        date: '2024-08-01',
        details: 'Order delivered on time. The product is in perfect condition and works well.'
    },
    {
        id: 'ORDER12346',
        productName: 'Bluetooth Speaker',
        imageUrl: 'https://via.placeholder.com/100',
        quantity: 2,
        price: 45.00,
        status: 'pending',
        date: '2024-08-10',
        details: 'Order is being processed and will be shipped soon. Expected delivery within 5 days.'
    },
    {
        id: 'ORDER12347',
        productName: 'Smartphone Case',
        imageUrl: 'https://via.placeholder.com/100',
        quantity: 1,
        price: 15.99,
        status: 'canceled',
        date: '2024-08-05',
        details: 'Order canceled due to stock issues. Refund has been initiated.'
    }
];

function displayOrders() {
    const ordersContainer = document.getElementById('orders');
    orders.forEach(order => {
        const orderElement = document.createElement('div');
        orderElement.classList.add('order');
        
        orderElement.innerHTML = `
            <img src="${order.imageUrl}" alt="${order.productName}">
            <div class="order-info">
                <h3>Order ID: ${order.id}</h3>
                <p><strong>Product:</strong> ${order.productName}</p>
                <p><strong>Quantity:</strong> ${order.quantity}</p>
                <p><strong>Price:</strong> $${order.price.toFixed(2)}</p>
                <p><strong>Date:</strong> ${order.date}</p>
                <span class="expand-details">View Details</span>
                <p class="order-details" style="display: none;">${order.details}</p>
            </div>
            <div class="order-status">
                <span class="status ${order.status}">${order.status.charAt(0).toUpperCase() + order.status.slice(1)}</span>
            </div>
        `;
        
        ordersContainer.appendChild(orderElement);

        // Add event listener to toggle the details
        const detailsToggle = orderElement.querySelector('.expand-details');
        const detailsParagraph = orderElement.querySelector('.order-details');

        detailsToggle.addEventListener('click', () => {
            const isVisible = detailsParagraph.style.display === 'block';
            detailsParagraph.style.display = isVisible ? 'none' : 'block';
            detailsToggle.textContent = isVisible ? 'View Details' : 'Hide Details';
        });
    });
}

// Initialize the page by displaying orders
displayOrders();
