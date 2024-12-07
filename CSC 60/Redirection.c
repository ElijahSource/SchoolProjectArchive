// Author(s): names here

/* ----------------------------------------------------------------- */
/*                  Redirection, part of a child process                */
/* ----------------------------------------------------------------- */

#include "lab9_10.h"

void Redirection(int argc, char *argv[])
{
    int i;	     // loop counter
    int locout = 0;  // track position of location Out redirection, >
    int locin = 0;   // track position of location In redirection, <
    
    for(i = 0; i < argc; i++)
    {
        if(strcmp(argv[i], ">") == 0) 
        {
            if(locout != 0) // if attempting to output to more than one file
            {
		fprintf(stderr, "Cannot output to more than one file.\n");//error output
		_exit(EXIT_FAILURE);
            }
            else if (i == 0)//if no command entered 
            {
                fprintf(stderr, "No command entered.\n");//error output
                _exit(EXIT_FAILURE);//exit command as error
            }
            locout = i;
            } 
            else if (strcmp(argv[i], "<") == 0) {
            if (locin != 0) //if trying to input from more than one file
            {
                fprintf(stderr, "Cannot input from more than one file.\n");//error output
                _exit(EXIT_FAILURE);
            } 
            else if (i == 0) 
            {
                fprintf(stderr, "No command entered.\n");
                _exit(EXIT_FAILURE);
            }
            locin = i;
        }
    }

    if (locout != 0) {
        if (argv[locout + 1] == NULL) {//determines no output file
            fprintf(stderr, "No output file specified.\n");//print error
            _exit(EXIT_FAILURE);
        }

        int fd = open(argv[locout + 1], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);//flags for permissions
        if (fd == -1) {
            perror("Error opening output file");//error output
            _exit(EXIT_FAILURE);
        }

        dup2(fd, STDOUT_FILENO);//call dup2 to swithc stantardout to the value of the file descriptor 
        close(fd);//close the file
        argv[locout] = NULL;//set things for future exec by setting argv[lcout] to null
    }

    if (locin != 0) {
        if (argv[locin + 1] == NULL) {
            fprintf(stderr, "No input file specified.\n");
            _exit(EXIT_FAILURE);
        }

        int fd = open(argv[locin + 1], O_RDONLY);//permission
        if (fd == -1) {
            perror("Error opening input file");//error output
            _exit(EXIT_FAILURE);
        }

        dup2(fd, STDIN_FILENO);
        close(fd);
        argv[locin] = NULL;
    }


    
}  /*end of function*/

