/* Author(s): Ruthann Biel & Elijah ******
 * This is csc60mshell.c
 * This is lab9.c the csc60mshell
 * This program serves as the base for doing labs 9 and 10.
 * Student is required to use this program to build a mini shell
 * using the specification as documented in the directions.
 * Date: Fall 2023
 */

#include "lab9_10.h"


/* ----------------------------------------------------------------- */
/*                  The main program starts here                     */
/* ----------------------------------------------------------------- */
int main(void)
{
    char CmdLine[MAXLINE];
    char *argv[MAXARGS];
    int  argc;

    // Loop forever to wait and process commands 
    while (TRUE) 
    {
        printf("\ncsc60msh > "); //change the prompt

        /* read the command the user types in */
        if (fgets(CmdLine, MAXLINE, stdin) == NULL)
        {
          break;
        }

        /* Call ParseCmd to build argc/argv; their limits declared in lab9_10.h */
        argc = ParseCmd(CmdLine, argv);

        // REQUIRED: Just-to-make-sure printfs 
        printf("Argc = %i \n",  argc);
        int i;
        for(i = 0; i < argc; i++)
        {
            printf ("Argv %i = %s \n", i, argv[i]);
        }

        // If user hits enter key without a command, continue to loop again at the beginning 
        // You write this line.  Hint: if argc is zero, no command declared */
        if (argc == 0)
        {
          continue;
        }


        // Handle build-in command: exit, pwd, or cd 
        // See the directions for the algorithms to do these 3 commands
        if (strcmp(argv[0], "exit") == 0)
        {
            // issue an exit call
            break; // exit the while loop
        }
        else if (strcmp(argv[0], "pwd") == 0)
        {
            char PathBuf[MAX_PATH_LENGTH];//char variable arry pathbuf of size max_path_length to hold path

            getcwd(PathBuf, sizeof(PathBuf));//do getcwd

            printf("%s\n", PathBuf);//print pathbuf
            continue; // to end of while loop
        }
        else if (strcmp(argv[0], "cd") == 0)
        {
            char *dir;//declare a char variable dir as a pointer

            if (argc == 1)
            {
                dir = getenv("HOME");//getenv call with home
            }
            else
            {
                dir = argv[1];
            }

            if (chdir(dir) != 0)
            {
                perror("error changing directory");//error checking
            }
            continue;
        }

        // Else, fork off a process 
        // Leave this section alone forlab9, changes will happen in lab10
        else
        {
            // RunExtCmd(int argc, char argv);

        } // end of the if(exit)-else-if 

    } // end of the while(TRUE) loop 
    return 0;
} // end of main 

