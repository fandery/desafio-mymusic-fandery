import React, { useEffect } from 'react';
import logo from './logo.svg';
import { makeStyles, Theme, createStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Checkbox from '@material-ui/core/Checkbox';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import { FormControlLabel, InputAdornment, Radio, RadioGroup, TextField } from '@material-ui/core';
import ClearIcon from '@material-ui/icons/Clear';
import SearchIcon from '@material-ui/icons/Search';
import $ from 'jquery'
import Music from '../../models/Music';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
      margin: 'auto',
      height: 600,            
    },
    paper: {
      width: 800,
      height: 600,
      overflow: 'auto',
    },
    button: {
      margin: theme.spacing(0.5, 0),
    },
    table: {
      minWidth: 5,  
                 
    },
    body: {
      minHeight: 10,
      height: 100,
      alignItems: 'center',
    },
    container: {
      minHeight: 300,
      width: 500,
      overflow: 'auto'
    },
  }),
);

interface Props {  
  handleChecked: (music: Music[]) => void;
  handleMusicToRemove: (music: Music) => void;
  items: Music[];
  side: string
}

const PlaylistTable: React.FC<Props> = ({ items, side, handleChecked, handleMusicToRemove}) => {

  const classes = useStyles();
  const [checked, setChecked] = React.useState<Music[]>([]);
  const [musicToRemove, setMusicToRemove] = React.useState<Music>();

  const handleToggle = (value: Music) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
    handleChecked(newChecked);
  };

  const handleChange = (music: Music) => {
    setMusicToRemove(music);
    handleMusicToRemove(music);
  };
  
  return (
      <>
            <TableContainer component={Paper} className={classes.container}>
              <Table className={classes.table} aria-label="simple table">
                <TableHead>
                  <TableRow>
                    <TableCell>Music Name</TableCell>
                    <TableCell align="right">Artist name</TableCell>
                    <TableCell />
                  </TableRow>
                </TableHead>
                <TableBody className={classes.body}>
                {items.length !== 0 ? 
                  items.map((row) => (                  
                    <TableRow key={row.nome}>
                      <TableCell component="th" scope="row">
                        {row.nome}
                      </TableCell>
                      <TableCell align="right">{row.artista.nome}</TableCell>
                      <TableCell>
                    {side === 'left' ?
                      <Checkbox
                        checked={checked.indexOf(row) !== -1}
                        tabIndex={-1}
                        disableRipple
                        inputProps={{ 'aria-labelledby': `transfer-list-item-${row.id}-label`}}
                        onClick={handleToggle(row)}
                      />
                      :
                      <RadioGroup aria-label="mp" name="musPlaylist" value={musicToRemove} onChange={() => handleChange(row)}>
                        <FormControlLabel value={row.nome} control={<Radio checked={row.id === musicToRemove?.id}/>} label=''/>
                      </RadioGroup>                  
                      }
                      </TableCell>
                    </TableRow>
                  ))
                  : 
                  <TableRow>
                    <TableCell colSpan={3}>
                      <div style={{textAlign: 'center', width: '100%'}}>No musics</div>
                    </TableCell>
                  </TableRow>
                }
                </TableBody>
              </Table>
          </TableContainer>
      </>
    );
  };

  export default PlaylistTable;