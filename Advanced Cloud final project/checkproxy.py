import PySimpleGUI as sg
import sys
import json
import requests
# ----------- Create the 3 layouts this Window will display -----------
layout1 = [  
          [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1), key='actor_id_old', background_color='white', text_color='black')],
           
          [sg.Text('New Valume',size=(20, 1), font='Lucida',justification='left')],

          [sg.T('First Name:', pad=((3,0),0)), sg.In(size=(44,1),key='fname_new',  background_color='white', text_color='black')],
          [sg.T('Last Name:', pad=((3,0),0)), sg.In(size=(44,1),key='lname_new',  background_color='white', text_color='black')],
        
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-update', background_color='white', text_color='black')],

          [sg.Button('RUN', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
          ]

layout2 = [  
          [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1),key='actor_id_old-delete', background_color='white', text_color='black')],
          
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-delete', background_color='white', text_color='black')],

         
          [sg.Button('RUN2', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
          ]

layout3 = [  
          [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1), key='actor_id_update', background_color='white', text_color='black')], 
          [sg.T('First Name:', pad=((3,0),0)), sg.In(size=(44,1),key='fname_new_update',  background_color='white', text_color='black')],
          [sg.T('Last Name:', pad=((3,0),0)), sg.In(size=(44,1),key='lname_new_update',  background_color='white', text_color='black')],
        
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-insert', background_color='white', text_color='black')],

          [sg.Button('RUN3', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
]
layout4 = [  
          [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1),key='actor_id_old-select1', background_color='white', text_color='black')],
          
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-select1', background_color='white', text_color='black')],

         
          [sg.Button('RUN4', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
       
          ]

layout5 = [  
           [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1),key='actor_id_old-select2', background_color='white', text_color='black')],
          
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-select2', background_color='white', text_color='black')],

         
          [sg.Button('RUN5', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
       
           ]

layout6 = [
          [sg.Text('Choose your data to Change',size=(20, 1), font='Lucida',justification='left')],
          [sg.T('Actor ID:', pad=((18,0),0)), sg.In(size=(44,1),key='actor_id_old-select3', background_color='white', text_color='black')],
          
          [sg.T('Query Result:',pad=((5,0),44)), sg.MLine(size=(44,5),key = 'result-select3', background_color='white', text_color='black')],

         
          [sg.Button('RUN6', font=('Times New Roman',12)),sg.Button('CANCEL', font=('Times New Roman',12)), sg.Exit()]
       
          ]
        

# ----------- Create actual layout using Columns and a row of Buttons
layout = [
     [sg.Button('Update'), sg.Button('Delete'), sg.Button('Insert'),
           sg.Button('select from Master'), sg.Button('select randrom from slaves'), sg.Button('select from best ping')
           ],
    [sg.Column(layout1, key='-Update-'), sg.Column(layout2, visible=False, key='-Delete-'), sg.Column(layout3, visible=False, key='-Insert-'),sg.Column(layout4,visible=False, key='-select from Master-'),sg.Column(layout5,visible=False ,key='-select randrom from slaves-'),sg.Column(layout6,visible=False ,key='-select from best ping-')]
          
         ]

window = sg.Window("ADVANCE CLOUD FINAL PROJECT", layout=layout, background_color="#972533", size=(800, 400))

#window = sg.Window('Swapping the contents of a window', layout)

layout = "Update"  # The currently visible layout
while True:
    event, values = window.read()
    print(event, values)
 
    
    if event in (None, 'Exit'):
        break
    if event == 'RUN' :
        table_name = "actor"
        actor_id = 201
        old_value = 'reza'
        new_value = 'rez123'
        fname = 'Reza2'
        lname = 'rooooooooooh'

        actor_id = values['actor_id_old']
        fname = values['fname_new']
        lname = values['lname_new']

        
        input = [{'table': table_name, 'old_value': old_value, 'new_value': new_value , 'actor_id_old' : actor_id, 'fname' :fname, 'lname' : lname}]
        josn_input = json.dumps(input)
        res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/update/",json=josn_input)
        
        window.Element('result-update').update(value=res.text)
        
    if event == 'RUN2' :
            table_name = "actor"
            actor_id = values['actor_id_old-delete']
            input = [{'table': table_name, 'actor_id' : actor_id}]

            josn_input = json.dumps(input)
            res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/delete/",json=josn_input)
            window.Element('result-delete').update(value=res.text)

    if event == 'RUN3' :
            table_name = "actor"
            actor_id = values['actor_id_update']
            fname = values['fname_new_update']
            lname = values['lname_new_update']
            input = [{'table': table_name, 'actor_id' : actor_id,'fname' :fname, 'lname' : lname}]

            josn_input = json.dumps(input)
            res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/insert/",json=josn_input)
            window.Element('result-insert').update(value=res.text)
    if event == 'RUN4' :
            table_name = "actor"
            actor_id = values['actor_id_old-select1']
            input = [{'table': table_name, 'actor_id' : actor_id}]
            print(input)
            josn_input = json.dumps(input)
            res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/select1/",json=josn_input)
            print(res)
            window.Element('result-select1').update(value=res.text)
    if event == 'RUN5' :
        table_name = "actor"
        actor_id = values['actor_id_old-select2']
        input = [{'table': table_name, 'actor_id' : actor_id}]
        josn_input = json.dumps(input)
        res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/select2/",json=josn_input)
        window.Element('result-select2').update(value=res.text)
    if event == 'RUN6' :
        table_name = "actor"
        actor_id = values['actor_id_old-select3']
        input = [{'table': table_name, 'actor_id' : actor_id}]
        josn_input = json.dumps(input)
        res = requests.post("http://ec2-52-91-162-51.compute-1.amazonaws.com/select3/",json=josn_input)
        window.Element('result-select3').update(value=res.text)
    if event == sg.WIN_CLOSED or event == 'Exit':
        window.close()
        break
    elif event in 'UpdateDeleteInsertExit select from Master select randrom from slaves select from best ping':
        window[f'-{layout}-'].update(visible=False)
        window[f'-{event}-'].update(visible=True)
        layout = event
window.close()