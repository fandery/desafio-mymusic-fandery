import React, { useEffect } from 'react';
import logo from './logo.svg';
import { Section, PanelSearch, Header, Container} from './styles'
import { makeStyles, Theme, createStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Music from './models/Music';
import { FormControlLabel, InputAdornment, Radio, RadioGroup, TextField } from '@material-ui/core';
import ClearIcon from '@material-ui/icons/Clear';
import SearchIcon from '@material-ui/icons/Search';
import { selectMusicsByFilter, selectPlaylistByUser, addMusics, removeMusic} from './services/index'
import $ from 'jquery'
import PlaylistTable from './components/Table/PlaylistTable';

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

function not(a: Music[], b: Music) {
  return a.filter((value) => value !== b);
}

function intersection(a: Music[], b: Music[]) {
  return a.filter((value) => b.indexOf(value) !== -1);
}


function App() {

  const classes = useStyles();
  const [checked, setChecked] = React.useState<Music[]>([]);
  const [musics, setMusics] = React.useState<Music[]>([]);
  const [left, setLeft] = React.useState<Music[]>([]);
  const [right, setRight] = React.useState<Music[]>([]);
  const [searchMusic, setSearchMusic] = React.useState<string>('');
  const [searchMusicTerm, setSearchMusicTerm] = React.useState<boolean>(false);
  const [searchUser, setSearchUser] = React.useState<string>('');  
  const [msgErrorSearchMusic, setMsgErrorSearchMusic] = React.useState<string>();
  const [msgErrorSearchUser, setMsgErrorSearchUser] = React.useState<string>();
  const [playlistId, setPlaylistId] = React.useState<string>('');
  const [musicToRemove, setMusicToRemove] = React.useState<Music>();
  const leftChecked = intersection(checked, left);
  const rightChecked = intersection(checked, right);
  const [userFound, setUserFound] = React.useState<boolean>(false);


  useEffect( () => {
      
    setMsgErrorSearchMusic("");
    setMsgErrorSearchUser("");
        
  }, []);

  const getMusic = () => {

    selectMusicsByFilter(searchMusic)
				.then(response => {
          setMsgErrorSearchMusic("");          
					if (response.status === 200) {                       
            setLeft(response.data)						
					}else if (response.status === 204) {
            setLeft([])						
          }else if (response.status === 400) {
            setMsgErrorSearchMusic("O número de caracteres da busca deve ser maior ou igual a 3.");
          }           
				})
  }

  const getUser = () => {

    selectPlaylistByUser(searchUser)
				.then(response => {
          if (response.status === 200) {   
            setUserFound(true);                    
            setMsgErrorSearchUser("");          
            setPlaylistId(response.data.id)				            
            setRight(response.data.musics)		
					}else if (response.status === 204) {
            setRight([])	        
          }else if (response.status === 404) {
            setMsgErrorSearchUser("Usuário não encontrado.");
            setRight([])
            setPlaylistId('')		
            setUserFound(false);
          }           
				})    
  }

  const addMusicsToPlaylist = (musics: Music[]) => {
    
    addMusics(playlistId, musics)
				.then(response => {          
					if (response.status === 200) {                       

					}else if (response.status === 204) {
            setRight([])	        
          }else if (response.status === 404) {            
            setRight([])
          }           
				})    
  }

  const removeMusicToPlaylist = (music: Music) => {
    
    removeMusic(playlistId, music.id)
				.then(response => {          
					if (response.status === 200) {                       
            //setRight(response.data.musics)						
					}else if (response.status === 204) {
            setRight([])	        
          }else if (response.status === 404) {
            setMsgErrorSearchUser("Usuário não encontrado.");
            setRight([])
          }           
				})    
  }

  const handleCheckedRight = () => {        
    if(userFound){
      setMsgErrorSearchUser("");
      setRight(right.concat(leftChecked));    
      addMusicsToPlaylist(right.concat(leftChecked));
    }else{
      setMsgErrorSearchUser("Selecione um usuário.")
    }
  };

  const handleCheckedLeft = () => {    
    if(musicToRemove !== undefined){
      setRight(not(right, musicToRemove));    
      removeMusicToPlaylist(musicToRemove);
    }
  };
  
  const handleChange = (music: Music) => {
    setMusicToRemove(music);
  };

  const handleChecked = (musics: Music[]) => {
    setChecked(musics);
  }

  return (
    <>
      <Container>
          <Header>Desafio - My Music</Header>
          <Grid container spacing={3} justify="center" alignItems="center" className={classes.root}>
            <Section>
              <PanelSearch>
                <div style={{display: 'flex', flexDirection: 'row'}}>
                  <div style={{paddingRight: '5px', marginTop: 'auto'}}>Music or artist:</div>
                  <TextField
                      id="searchMusic"                      
                      className="searchMusicInput"
                      InputProps={{
                          endAdornment: (
                              <InputAdornment position="start">
                                  {searchMusic ? (
                                      <ClearIcon style={{ cursor: 'pointer' }} onClick={() => {  
                                        setSearchMusic("")   
                                        $(`.searchMusicInput`).find("input").val('');                                                                                                                                  
                                      }} />
                                  ) : (
                                        <SearchIcon />
                                      )}
                              </InputAdornment>
                          )
                      }}  
                      onChange={(e) => {                    
                          setSearchMusic(e.target.value);                  
                      }}

                      onKeyDown={(e) =>{
                        if(e.key === 'Enter'){
                          getMusic();
                        }
                      }}
                  />              
                </div>
                <small style={{paddingBottom: '50px'}}>
                  {msgErrorSearchMusic && (
                      <span style={{color: 'red'}}>
                      <br/>
                        {msgErrorSearchMusic}
                      </span>
                  )}
                </small>
              </PanelSearch>
              <Grid style={{overflow: 'auto', minHeight: '300px'}} item>
                <PlaylistTable items={left} side='left' handleChecked={handleChecked} handleMusicToRemove={handleChange}/>              
              </Grid>
            </Section> 
          
            
            <Grid item>
              <Grid container direction="column" alignItems="center">              
                <Button
                  variant="outlined"
                  size="small"
                  className={classes.button}
                  onClick={handleCheckedRight}
                  disabled={leftChecked.length === 0}
                  aria-label="move selected right"
                  style={{marginBottom: '50px'}}
                >
                  ≫
                </Button>
                <Button
                  variant="outlined"
                  size="small"
                  className={classes.button}
                  onClick={handleCheckedLeft}                
                  aria-label="move selected left"                
                >
                  ≪
                </Button>              
              </Grid>
            </Grid>
            <Section>
              <PanelSearch>
                <div style={{display: 'flex', flexDirection: 'row'}}>
                  <div style={{paddingRight: '5px', marginTop: 'auto'}}>User:</div>
                  <TextField
                        id="searchUser"                      
                        className="searchUserInput"
                        InputProps={{
                            endAdornment: (
                                <InputAdornment position="start">
                                    {searchUser ? (
                                        <ClearIcon style={{ cursor: 'pointer' }} onClick={() => {     
                                          setSearchUser("")   
                                          $(`.searchUserInput`).find("input").val('');                                                                                                                         
                                        }} />
                                    ) : (
                                          <SearchIcon />
                                        )}
                                </InputAdornment>
                            )
                        }}
                        onChange={(e) => {                    
                          setSearchUser(e.target.value);                  
                        }}

                        onKeyDown={(e) =>{
                          if(e.key === 'Enter'){
                            getUser();
                          }
                        }}                               
                    />
                </div>
                <small style={{paddingBottom: '50px'}}>
                  {msgErrorSearchUser && (
                      <span style={{color: 'red'}}>
                      <br/>
                        {msgErrorSearchUser}
                      </span>
                  )}
                </small>
                </PanelSearch>
                <Grid style={{overflow: 'auto', minHeight: '300px'}} item>
                  <PlaylistTable items={right} side='right' handleChecked={handleChecked} handleMusicToRemove={handleChange}/>              
              </Grid>
            </Section>      
          </Grid>
      </Container>
    </>
  );
}

export default App;
