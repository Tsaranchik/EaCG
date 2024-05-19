let crystalSprite = document.getElementById("sprite0");
let fireSprite = document.getElementById("sprite1");

let crystalVariations = ["./images/crystal0.png", "./images/crystal1.png", "./images/crystal2.png", "./images/crystal3.png", "./images/crystal4.png"];
let fireVariations = ["./images/fire0.png", "./images/fire1.png", "./images/fire2.png", "./images/fire3.png", "./images/fire4.png"];

let crystalIndex = 0;
let fireIndex = 0;

crystalSprite.addEventListener("click", () => {
    crystalIndex = (crystalIndex + 1) % crystalVariations.length;
    crystalSprite.style.backgroundImage = "url('" + crystalVariations[crystalIndex] + "')";
});

fireSprite.addEventListener("click", () => {
    fireIndex = (fireIndex + 1) % fireVariations.length;
    fireSprite.style.backgroundImage = "url('" + fireVariations[fireIndex] + "')";
});

let counter = 0;
function animateSprite() {
    fireIndex = (fireIndex + 1) % fireVariations.length;
    fireSprite.style.backgroundImage = "url('" + fireVariations[fireIndex] + "')";
    counter++;

    if (counter === 15) {
        counter = 0;
        crystalIndex = (crystalIndex + 1) % crystalVariations.length;
        crystalSprite.style.backgroundImage = "url('" + crystalVariations[crystalIndex] + "')";
    }
}

setInterval(animateSprite, 1000 / fireVariations.length);