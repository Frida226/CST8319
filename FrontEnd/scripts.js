document.addEventListener('DOMContentLoaded', function() {
    const cart = [];
    const favorites = [];
    let allProducts = [];
    const initialDisplayCount = 9;
    let displayedCount = initialDisplayCount;

    // Function to fetch and render products from JSON
    function fetchProducts() {
        fetch('../flowers.json')  // Updated file name
            .then(response => response.json())
            .then(products => {
                allProducts = products;
                renderProducts(products.slice(0, initialDisplayCount));
                if (products.length > initialDisplayCount) {
                    showMoreButton.style.display = 'block'; // Show the "Show More" button if there are more products
                }
            })
            .catch(error => console.error('Error loading products:', error));
    }

    function renderProducts(products) {
        const productList = document.getElementById('product-list');
        productList.innerHTML = '';

        products.forEach(product => {
            const productCard = document.createElement('div');
            productCard.className = 'col-md-4 mb-4';
            productCard.innerHTML = `
                <div class="card">
                    <img src="${product.image}" class="card-img-top" alt="${product.name}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">${product.description}</p>
                        <p class="card-text">$${product.price.toFixed(2)}</p>
                        <button class="btn btn-primary add-to-cart" data-name="${product.name}">Add to Cart</button>
                        <button class="btn btn-outline-secondary add-to-favorites" data-name="${product.name}">Add to Favorites</button>
                    </div>
                </div>
            `;
            productList.appendChild(productCard);
        });
    }

    function updateCart() {
        const cartItems = document.getElementById('cart-items');
        cartItems.innerHTML = '';
        cart.forEach(item => {
            cartItems.innerHTML += `<p>${item.name} - $${item.price.toFixed(2)}</p>`;
        });
        document.getElementById('cart-count').innerText = cart.length;
    }

    function updateFavorites() {
        const favoritesItems = document.getElementById('favorites-items');
        favoritesItems.innerHTML = '';
        favorites.forEach(item => {
            favoritesItems.innerHTML += `<p>${item.name}</p>`;
        });
        document.getElementById('favorites-count').innerText = favorites.length;
    }

    function addToCart(productName) {
        const product = allProducts.find(p => p.name === productName);
        if (product) {
            cart.push(product);
            updateCart();
        }
    }

    function addToFavorites(productName) {
        const product = allProducts.find(p => p.name === productName);
        if (product) {
            if (!favorites.some(f => f.name === productName)) {
                favorites.push(product);
                updateFavorites();
            }
        }
    }

    document.getElementById('product-list').addEventListener('click', function(event) {
        if (event.target.classList.contains('add-to-cart')) {
            const productName = event.target.dataset.name;
            addToCart(productName);
        } else if (event.target.classList.contains('add-to-favorites')) {
            const productName = event.target.dataset.name;
            addToFavorites(productName);
        }
    });

    document.getElementById('cart-button').addEventListener('click', function() {
        $('#cart-modal').modal('show');
    });

    document.getElementById('favorites-button').addEventListener('click', function() {
        $('#favorites-modal').modal('show');
    });

    document.getElementById('search-bar').addEventListener('input', function(event) {
        const searchTerm = event.target.value.toLowerCase();
        const filteredProducts = allProducts.filter(product => product.name.toLowerCase().includes(searchTerm));
        renderProducts(filteredProducts.slice(0, displayedCount));
        if (filteredProducts.length > displayedCount) {
            showMoreButton.style.display = 'block'; // Show the "Show More" button if there are more filtered products
        } else {
            showMoreButton.style.display = 'none'; // Hide the button if all filtered products are shown
        }
    });

    document.getElementById('show-more-button').addEventListener('click', function() {
        displayedCount += 12; // Increase the number of products displayed
        const productsToShow = allProducts.slice(0, displayedCount);
        renderProducts(productsToShow);
        if (displayedCount >= allProducts.length) {
            showMoreButton.style.display = 'none'; // Hide the "Show More" button if all products are shown
        }
    });

    const showMoreButton = document.getElementById('show-more-button');
    showMoreButton.style.display = 'none'; // Hide the "Show More" button initially

    // Initial call to fetch and render products
    fetchProducts();
});
