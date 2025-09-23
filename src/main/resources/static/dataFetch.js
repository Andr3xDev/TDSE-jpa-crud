// URL del back
const API_BASE_URL = 'http://localhost:8080/api/inventory';

// Get data from the html
const createForm = document.getElementById('create-form');
const searchForm = document.getElementById('search-form');
const resultsTableBody = document.getElementById('results-table-body');
const messageArea = document.getElementById('message-area');

createForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    
    const formData = new FormData(createForm);
    const data = {
        name: formData.get('name'),
        address: formData.get('address'),
        price: parseInt(formData.get('price'), 10),
        size: parseInt(formData.get('size'), 10),
        description: formData.get('description')
    };

    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Error al crear el artículo.');
        }

        const createdItem = await response.json();
        showMessage(`Artículo "${createdItem.name}" creado con éxito (ID: ${createdItem.id}).`, 'success');
        createForm.reset();
    } catch (error) {
        showMessage(error.message, 'error');
    }
});

searchForm.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(searchForm);
    const params = new URLSearchParams();

    if (formData.get('name')) params.append('name', formData.get('name'));
    if (formData.get('address')) params.append('address', formData.get('address'));
    if (formData.get('maxPrice')) params.append('maxPrice', formData.get('maxPrice'));
    if (formData.get('minSize')) params.append('minSize', formData.get('minSize'));
    
    try {
        const response = await fetch(`${API_BASE_URL}/search?${params.toString()}`);

        if (!response.ok) {
            throw new Error('Error al realizar la búsqueda.');
        }
        
        const page = await response.json();
        displayResults(page.content);
    } catch (error) {
        showMessage(error.message, 'error');
    }
});

function displayResults(items) {
    resultsTableBody.innerHTML = '';

    if (items.length === 0) {
        resultsTableBody.innerHTML = '<tr><td colspan="5" style="text-align:center;">No se encontraron resultados.</td></tr>';
        return;
    }

    items.forEach(item => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.address}</td>
            <td>${item.price}</td>
            <td>${item.size}</td>
        `;
        resultsTableBody.appendChild(row);
    });
}

function showMessage(message, type) {
    messageArea.textContent = message;
    messageArea.className = type;
    messageArea.style.display = 'block';

    setTimeout(() => {
        messageArea.style.display = 'none';
    }, 5000);
}