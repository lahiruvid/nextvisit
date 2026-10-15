import { useState } from "react";

const empty = { customerName: "", visitDate: "", serviceType: "" };

export default function App() {
  const [form, setForm] = useState(empty);
  const [visit, setVisit] = useState(null);
  const [reminder, setReminder] = useState(null);
  const [lookupId, setLookupId] = useState("");
  const [message, setMessage] = useState("");

  function change(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  async function save(event) {
    event.preventDefault();
    setMessage("");
    setReminder(null);
    const response = await fetch("/api/visits", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form)
    });
    if (!response.ok) {
      setVisit(null);
      setMessage("Not saved. Name, date, and service are all required.");
      return;
    }
    const saved = await response.json();
    setVisit(saved);
    setLookupId(saved.id);
    setMessage("Saved. The reminder is queued in the background.");
    window.setTimeout(() => loadReminder(saved.id), 2000);
  }

  async function load(event) {
    event.preventDefault();
    setMessage("");
    const response = await fetch("/api/visits/" + lookupId);
    if (!response.ok) {
      setVisit(null);
      setMessage("No visit with that id.");
      return;
    }
    setVisit(await response.json());
    loadReminder(lookupId);
  }

  async function loadReminder(id) {
    const response = await fetch("/api/reminders/" + id);
    setReminder(response.ok ? await response.json() : null);
  }

  return (
    <main>
      <h1>NextVisit</h1>
      <p>Save a visit. A reminder is queued after it is stored.</p>

      <form onSubmit={save}>
        <label>
          Customer name
          <input name="customerName" value={form.customerName} onChange={change} />
        </label>
        <label>
          Visit date
          <input name="visitDate" type="date" value={form.visitDate} onChange={change} />
        </label>
        <label>
          Service
          <input name="serviceType" value={form.serviceType} onChange={change} />
        </label>
        <button type="submit">Save visit</button>
      </form>

      <form onSubmit={load}>
        <label>
          Find by id
          <input value={lookupId} onChange={(event) => setLookupId(event.target.value)} />
        </label>
        <button type="submit">Find</button>
      </form>

      {message ? <p className="note">{message}</p> : null}

      {visit ? (
        <section>
          <h2>Stored visit</h2>
          <p>Id: {visit.id}</p>
          <p>Name: {visit.customerName}</p>
          <p>Date: {visit.visitDate}</p>
          <p>Service: {visit.serviceType}</p>
          <p>Status: {visit.status}</p>
          <p>Reminder: {reminder ? reminder.status : "not ready yet"}</p>
        </section>
      ) : null}
    </main>
  );
}
