document.getElementById("category-dropdown").addEventListener("change", function () {

    let categoryValue = this.value; // Get the selected value of dropdown1
    let jsonObject = JSON.parse(categoryValue);
    let productTypeDropDown = document.getElementById("product-dropdown");

    // Clear existing options in productTypeDropDown
    productTypeDropDown.innerHTML = "";
    productTypeDropDown.add(new Option("Select a Product Type", ""));

    jsonObject.products.forEach(function (product) {
        productTypeDropDown.add(new Option(product.product, JSON.stringify(product)));
    });
});

document.getElementById("product-dropdown").addEventListener("change", function () { 

    let prodDetails = this.value;  // map of json object
    let jsonObject = JSON.parse(prodDetails);
    
    let annualPrice = document.getElementById("annualprice");
    annualPrice.textContent = "$ " + jsonObject.priceAnnual + " OR";

    let monthlypriceamt = document.getElementById("monthlyprice-para");
    monthlypriceamt.textContent = "$ " + jsonObject.priceMonthly;
});

document.addEventListener("DOMContentLoaded", function () {
    let modal = document.getElementById("popupModal");
    let openModalBtn = document.getElementById("openModalBtn");
    let closeModalBtn = modal.querySelector(".close");

    // Function to open the modal
    function openModal() {
        modal.style.display = "block";
    }

    // Function to close the modal
    function closeModal() {
        modal.style.display = "none";
    }

    // Event listener for the button click to open modal
    openModalBtn.addEventListener("click", openModal);

    // Event listener for the close button click to close modal
    closeModalBtn.addEventListener("click", closeModal);

    // Event listener to close modal when clicking outside of it
    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            closeModal();
        }
    });
});
