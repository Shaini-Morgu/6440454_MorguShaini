// 1. JavaScript Basics & Setup

console.log("Welcome to the Community Portal");

window.onload = () => {
  alert("Page fully loaded!");
  renderEventOptions();
  renderEvents(events); // initial render
};

// 2. Syntax, Data Types, and Operators
const eventName = "Community Jazz Night";
const eventDate = "2025-06-01";
let availableSeats = 25;

console.log(`Event: ${eventName} Date: ${eventDate} Seats left: ${availableSeats}`);

// 3. Conditionals, Loops, and Error Handling

// Sample events array
const events = [
  {
    id: 1,
    name: "Community Jazz Night",
    category: "music",
    location: "Town Hall",
    date: "2025-06-01",
    seats: 25,
  },
  {
    id: 2,
    name: "Baking Workshop",
    category: "workshop",
    location: "Community Center",
    date: "2024-12-15",
    seats: 0, // full
  },
  {
    id: 3,
    name: "Open Mic Night",
    category: "music",
    location: "Cafe",
    date: "2025-05-20",
    seats: 10,
  },
];

// Filter out past or full events
function filterValidEvents(eventList) {
  const today = new Date();
  return eventList.filter((event) => {
    const eventDate = new Date(event.date);
    return eventDate >= today && event.seats > 0;
  });
}

// 4. Functions, Scope, Closures, Higher-Order Functions

function addEvent(event) {
  events.push(event);
}

function registerUser(eventId) {
  try {
    const event = events.find((e) => e.id === eventId);
    if (!event) throw new Error("Event not found");
    if (event.seats <= 0) throw new Error("No seats available");
    event.seats--;
    updateEventList();
    return true;
  } catch (error) {
    alert(`Registration failed: ${error.message}`);
    return false;
  }
}

// Closure to track registrations by category
function registrationTracker() {
  const counts = {};
  return function(category) {
    counts[category] = (counts[category] || 0) + 1;
    console.log(`Total registrations for ${category}: ${counts[category]}`);
  };
}
const trackRegistration = registrationTracker();

// Filter function with callback
function filterEventsByCategory(category, callback) {
  const filtered = category
    ? events.filter((event) => event.category === category)
    : events;
  callback(filtered);
}

// 5. Objects and Prototypes

class Event {
  constructor(id, name, category, location, date, seats) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.location = location;
    this.date = date;
    this.seats = seats;
  }

  checkAvailability() {
    return this.seats > 0;
  }
}

Event.prototype.details = function() {
  return `${this.name} at ${this.location} on ${this.date}`;
};

// 6. Arrays and Methods

// Add a new event using push
addEvent(new Event(4, "Art Workshop", "workshop", "Gallery", "2025-07-10", 15));

// Filter music events
const musicEvents = events.filter((event) => event.category === "music");

// Map to formatted display strings
const eventCards = events.map((event) => `<li>${event.name} - ${event.date}</li>`);

// 7. DOM Manipulation

const eventListEl = document.querySelector("#eventList");

function renderEvents(eventArray) {
  eventListEl.innerHTML = "";
  eventArray.forEach((event) => {
    const li = document.createElement("li");
    li.className = "eventCard";
    li.innerHTML = `
      <h3>${event.name}</h3>
      <p><strong>Category:</strong> ${event.category}</p>
      <p><strong>Location:</strong> ${event.location}</p>
      <p><strong>Date:</strong> ${event.date}</p>
      <p><strong>Seats Available:</strong> ${event.seats}</p>
      <button onclick="handleRegister(${event.id})">Register</button>
    `;
    eventListEl.appendChild(li);
  });
}

function renderEventOptions() {
  const select = document.querySelector("#eventSelect");
  select.innerHTML = "";
  events.forEach((event) => {
    if (event.seats > 0) {
      const option = document.createElement("option");
      option.value = event.id;
      option.textContent = event.name;
      select.appendChild(option);
    }
  });
}

// 8. Event Handling

function handleRegister(eventId) {
  if (registerUser(eventId)) {
    trackRegistration(events.find(e => e.id === eventId).category);
    renderEvents(filterValidEvents(events));
    renderEventOptions();
  }
}

// Filter by category onchange
document.querySelector("#categoryFilter").onchange = (e) => {
  filterEventsByCategory(e.target.value, (filteredEvents) => {
    renderEvents(filterValidEvents(filteredEvents));
  });
};

// Quick search by keydown
document.querySelector("#searchInput").onkeydown = (e) => {
  const searchTerm = e.target.value.toLowerCase();
  const filtered = events.filter((event) =>
    event.name.toLowerCase().includes(searchTerm)
  );
  renderEvents(filterValidEvents(filtered));
};

// 9. Async JS, Promises, Async/Await

async function fetchEvents() {
  try {
    showLoading(true);
    // Mock fetch with Promise
    const response = await new Promise((resolve) => {
      setTimeout(() => resolve({ json: () => events }), 1500);
    });
    const data = await response.json();
    renderEvents(filterValidEvents(data));
  } catch (err) {
    alert("Failed to load events");
  } finally {
    showLoading(false);
  }
}

function showLoading(show) {
  const loader = document.querySelector("#loader");
  if (!loader) {
    const div = document.createElement("div");
    div.id = "loader";
    div.textContent = "Loading...";
    document.body.appendChild(div);
  }
  document.querySelector("#loader").style.display = show ? "block" : "none";
}

// 10. Modern JavaScript Features

function cloneEvents(eventsList) {
  return [...eventsList];
}

function printEventDetails({ name, location }) {
  console.log(`Event: ${name}, Location: ${location}`);
}

printEventDetails(events[0]);

// 11. Working with Forms

const form = document.querySelector("#registrationForm");
const formMessage = document.querySelector("#formMessage");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  const { name, email, event: eventId } = e.target.elements;
  formMessage.textContent = "";

  if (!name.value || !email.value || !eventId.value) {
    formMessage.textContent = "All fields are required.";
    formMessage.style.color = "red";
    return;
  }

  if (!validateEmail(email.value)) {
    formMessage.textContent = "Invalid email address.";
    formMessage.style.color = "red";
    return;
  }

  if (registerUser(parseInt(eventId.value))) {
    formMessage.textContent = "Registration successful!";
    formMessage.style.color = "green";
    renderEvents(filterValidEvents(events));
    renderEventOptions();
    form.reset();
  } else {
    formMessage.textContent = "Registration failed.";
    formMessage.style.color = "red";
  }
});

function validateEmail(email) {
  return /\S+@\S+\.\S+/.test(email);
}

// 12. AJAX & Fetch API (Simulated)

function postRegistration(data) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (Math.random() > 0.2) resolve("Success");
      else reject("Failure");
    }, 1000);
  });
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const { name, email, event: eventId } = e.target.elements;

  if (!validateEmail(email.value) || !name.value || !eventId.value) return;

  try {
    formMessage.textContent = "Submitting registration...";
    await postRegistration({
      name: name.value,
      email: email.value,
      eventId: parseInt(eventId.value),
    });
    formMessage.textContent = "Registration submitted successfully!";
    formMessage.style.color = "green";
  } catch {
    formMessage.textContent = "Failed to submit registration.";
    formMessage.style.color = "red";
  }
});

// 13. Debugging and Testing
// Use Chrome DevTools console and network tab to debug the form submission, view logs above.

// 14. jQuery and JS Frameworks

// For demonstration, here's jQuery equivalent of register button click:
// $('#registerBtn').click(function() {
//   alert("Register button clicked!");
// });

// Benefit of frameworks: Efficient state management and reusable components.

