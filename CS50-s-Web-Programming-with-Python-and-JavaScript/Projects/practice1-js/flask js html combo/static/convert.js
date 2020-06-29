document.addEventListener('DOMContentLoaded', ()=>{
    document.querySelector('#convert_form').onsubmit = ()=>{
        
        // request
        const request = new XMLHttpRequest();
        const currency = document.querySelector('#currency').nodeValue;
        request.open('POST','/convert')

        // callback function for completing request
        request.onload = ()=>{
             // Update the result div
             if (data.success) {
                const contents = `1 USD is equal to ${data.rate} ${currency}.`
                document.querySelector('#result').innerHTML = contents;
            }
            else {
                document.querySelector('#result').innerHTML = 'There was an error.';
            }
        }

        // add data to send with request
        const data = new FormData();
        data.append('currency',currency);

        // send data
        request.send(data)
        return false;
    }
})