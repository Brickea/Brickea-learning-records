var number = 0;
function hello() {
    alert('hello world!');
}
function goodbye() {
    document.querySelector('h1').innerHTML = "GoodBye!"
}
function count_number() {
    document.querySelector('#countNumber').innerHTML = number++;
}