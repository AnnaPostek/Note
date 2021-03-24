import React from 'react';
import NoteService from '../services/NoteService';

class NoteComponent extends React.Component {
constructor(props) {
    super(props)
    this.state = {
        notes:[],
    };
}
componentDidMount() {
    NoteService.getNotes().then((response) => {
        this.setState({notes: response.data})
    });
}

render() {
    return (
        <div>
<h1 className="text-center">Notes List</h1>
<table className="table table-striped">
    <thead>
        <tr>
            <td>Note title:</td>
            <td>Date created</td>
            <td>Date modification</td>

        </tr>
    </thead>
    <tbody>
        {
            this.state.notes.map(
                note =>
                <tr key = {note.id}>
            <td>{note.title}</td>
            <td>{note.created}</td>
            <td>{note.modified}</td>
                </tr>
            )
        }
    </tbody>
</table>

        </div>
    )
}
}

export default NoteComponent;