
import Editor from '@/components/Editor'


export default [
    // Insert additional routes here
    { name: 'info', path: '/info', component: { name: 'InfoView', render: h => h('div', 'Info view') }, props: true },
    { name: 'default', path: '*', component: { name: 'DefaultView', render: h => h('div', '') }, props: true },
    {
        path: '/editor',
        name: 'editor',
        component: Editor,
        props: true
    },
]
